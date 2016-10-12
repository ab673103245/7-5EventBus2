package qianfeng.a7_5eventbus2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/10/12 0012.
 */
public class Right_fg extends Fragment {
    private TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_fg, container, false);

        tv = ((TextView) view.findViewById(R.id.tv));


        return view;
    }

    // 只要接收到那边的EventBus发送的消息，就会立即调用这个方法,但是Fragment的通信还是建议使用那两种方法！框架出了错很难排查。
    @Subscribe
    public void updata(String filename)
    {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getActivity().getAssets().open(filename)));
            String str = null;
            while((str = br.readLine())!=null)
            {
                buffer.append(str);
            }

            tv.setText(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().register(this);
    }
}
