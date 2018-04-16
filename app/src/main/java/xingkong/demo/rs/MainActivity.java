package xingkong.demo.rs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private Bitmap mBitmapIn;
    private Bitmap mBitmapOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBitmapIn = loadBitmap(R.drawable.data);
        mBitmapOut = Bitmap.createBitmap(mBitmapIn.getWidth(), mBitmapIn.getHeight(),
                mBitmapIn.getConfig());

        ImageView in = (ImageView) findViewById(R.id.displayin);
        in.setImageBitmap(mBitmapIn);

        ImageView out = (ImageView) findViewById(R.id.displayout);
        out.setImageBitmap(mBitmapOut);

        nativeRsMono(this.getCacheDir().toString(),
                mBitmapIn.getWidth(), mBitmapIn.getHeight(),
                mBitmapIn, mBitmapOut);
    }
    private Bitmap loadBitmap(int resource) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        return BitmapFactory.decodeResource(getResources(), resource, options);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    static {
        System.loadLibrary("native-lib");
    }
    public native void  nativeRsMono(String cacheDir, int X, int Y, Bitmap in, Bitmap out);
//    public native void  nativeNeonMono(String cacheDir, int X, int Y, Bitmap in, Bitmap out);
}
