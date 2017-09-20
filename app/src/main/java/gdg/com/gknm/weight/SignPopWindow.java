package gdg.com.gknm.weight;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.github.gcacace.signaturepad.views.SignaturePad;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gdg.com.gknm.R;
import gdg.com.gknm.constant.ConstantIndex;
import gdg.com.gknm.utils.SharedPreferenceUtil;
import gdg.com.gknm.utils.ToastUtils;
import gdg.com.gknm.utils.YjViewUtil;

import static gdg.com.gknm.R.id.btn_clean;
import static gdg.com.gknm.R.id.btn_save;
import static gdg.com.gknm.R.id.colse_pop;


/**
 * 右上角的Pop
 * Created by ${YeJun} on 2016/5/30.
 */
public class SignPopWindow extends PopupWindow {
    @Bind(R.id.tv_ed)
    TextView tvEd;
    @Bind(R.id.btn_save)
    Button btnStart;
    @Bind(R.id.btn_clean)
    Button btnStop;
    @Bind(R.id.signature_pad)
    SignaturePad signaturePad;
    private View conentView;
    private Activity mContext;
    private String mEd;

    public SignPopWindow(Activity context, String ed) {
        this.mContext = context;
        this.mEd = ed;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialo_sign, null);
        ButterKnife.bind(this, conentView);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.setClippingEnabled(false);//可以填充状态栏
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x4b000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.setAnimationStyle(R.style.take_photo_anim);
        initData();
    }

    private void initData() {
        tvEd.setText(mEd);
        if (TextUtils.equals(mEd, "企业现场负责人签字")) {
            if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_POLL, mContext) != null) {
                byte[] bigmapByte = (byte[]) SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_POLL, mContext);
                Bitmap signBitmap = YjViewUtil.getBitmap(bigmapByte);
                signaturePad.setSignatureBitmap(signBitmap);
            }
        } else if (TextUtils.equals(mEd, "第三方运维现场负责人签字")) {
            if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_THIRD, mContext) != null) {
                byte[] bigmapByte = (byte[]) SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_THIRD, mContext);
                Bitmap signBitmap = YjViewUtil.getBitmap(bigmapByte);
                signaturePad.setSignatureBitmap(signBitmap);
            }
        }else if (TextUtils.equals(mEd, "检查单位现场负责人签字")) {
            if (SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_UNIT, mContext) != null) {
                byte[] bigmapByte = (byte[]) SharedPreferenceUtil.getObject(ConstantIndex.SP_SIGN_UNIT, mContext);
                Bitmap signBitmap = YjViewUtil.getBitmap(bigmapByte);
                signaturePad.setSignatureBitmap(signBitmap);
            }
        }
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        } else {
        }
    }


    @OnClick({btn_clean, btn_save, colse_pop})
    public void onCLick(View view) {
        switch (view.getId()) {
            case btn_save:

                if(!signaturePad.isEmpty()){
                    Bitmap signBitmap = signaturePad.getTransparentSignatureBitmap();
                    byte[] bigmapByte = YjViewUtil.getBytes(signBitmap);
                    if (TextUtils.equals(mEd, "企业现场负责人签字")) {
                        SharedPreferenceUtil.saveObject(ConstantIndex.SP_SIGN_POLL, mContext, bigmapByte);
                    } else if (TextUtils.equals(mEd, "第三方运维现场负责人签字")) {
                        SharedPreferenceUtil.saveObject(ConstantIndex.SP_SIGN_THIRD, mContext, bigmapByte);
                    }else if (TextUtils.equals(mEd, "检查单位现场负责人签字")) {
                        SharedPreferenceUtil.saveObject(ConstantIndex.SP_SIGN_UNIT, mContext, bigmapByte);
                    }
                    ToastUtils.showShortToast(mContext, "保存成功");
                    dismiss();
                }else{
                    ToastUtils.showShortToast(mContext, "签字无效！");
                }

                break;
            case btn_clean:
                signaturePad.clear();
                break;
            case colse_pop:
                dismiss();
                break;
            default:
                break;
        }
    }


}




