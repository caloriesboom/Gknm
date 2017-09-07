package gdg.com.gknm.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import gdg.com.gknm.api.ApiService;
import gdg.com.gknm.utils.LogUtil;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import static gdg.com.gknm.api.AppConfig.API_BASE_URL;


/**
 * Created by ${Tom} on 2016/12/14.
 *
 */


public class RetorfitManager {
    private static volatile OkHttpClient sOkHttpClient;
    private static RetorfitManager mRetrofitManager=null;
    private ApiService mApiService;
    public Retrofit retrofit;


    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;



    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;


    public RetorfitManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(myGson ))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        LogUtil.d("创建了RetorfitManager");
        mApiService = retrofit.create(ApiService.class);
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetorfitManager.class) {
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(8, TimeUnit.SECONDS)
                            .readTimeout(8, TimeUnit.SECONDS)
                            .writeTimeout(8, TimeUnit.SECONDS)
                            .addInterceptor(mLoggingInterceptor)

                           .build();
                   /* .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)*/
                }
            }
        }
        return sOkHttpClient;
    }
    /**
     * 日志拦截
     */
    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
           // KLog.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
          LogUtil.d(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
          /*  LogUtil.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));*/
            return response;
        }
    };
    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
   /* private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                KLog.d("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
*/

    public static synchronized RetorfitManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetorfitManager();
        }
        return mRetrofitManager;
    }


    public static synchronized void dstroyInstance() {
        if (mRetrofitManager != null) mRetrofitManager = null;
        LogUtil.d("销毁了RetorfitManager");
    }
    public <T> T createReq(Class<T> reqServer)
    {
        return retrofit.create(reqServer);
    }

    final static class SafeTypeAdapterFactory implements TypeAdapterFactory {
        @Override
        public TypeAdapter create(Gson gson, final TypeToken type) {
            final TypeAdapter delegate = gson.getDelegateAdapter(this,type);

            return new TypeAdapter() {
                @Override
                public void write(JsonWriter out, Object value) throws IOException {
                    try {
                        delegate.write(out, value);
                    } catch (IOException e) {
                        delegate.write(out, null);
                    }
                }
                @Override
                public Object read(JsonReader in) throws IOException {

                    try {
                        return delegate.read(in);
                    } catch (IOException e) {
                        in.skipValue();
                        return null;
                    } catch (IllegalStateException e) {
                        in.skipValue();
                        return null;
                    } catch (JsonSyntaxException e) {
                        in.skipValue();
                        if (type.getType() instanceof Class) {
                            try {
                                return (Object) ((Class) type.getType()).newInstance();
                            } catch (Exception e1) {

                            }
                        }

                        return null;
                    }
                }

            };
        }
    }
    Gson myGson = new GsonBuilder().registerTypeAdapterFactory(new SafeTypeAdapterFactory()).create();


}
