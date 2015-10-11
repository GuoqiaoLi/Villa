package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.guoqiao.villa.R;

/**
 * Created by Guoqiao on 10/1/15.
 */
public class slideAdapter extends PagerAdapter {
    private int[] images = {R.drawable.bg, R.drawable.bg2, R.drawable.bg3};
    private Context context;
    private LayoutInflater inflater;

    public slideAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==(LinearLayout)o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.slider, container, false);
        ImageView imageView = (ImageView) item.findViewById(R.id.image_view);
//        Log.e("height", String.valueOf(imageView.));
        imageView.setImageResource(images[position]);

        container.addView(item);

        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
