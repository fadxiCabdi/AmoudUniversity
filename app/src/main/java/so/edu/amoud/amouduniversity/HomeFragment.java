package so.edu.amoud.amouduniversity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;


public class HomeFragment extends Fragment {

    private SliderLayout mDemoSlider;

    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        SliderLayout sliderShow = (SliderLayout) view.findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Welcome to Amoud University",R.drawable.banner);
        file_maps.put("President Of Amoud University",R.drawable.bus2);
        file_maps.put(" Amoud University Bus",R.drawable.bus1);
        file_maps.put("Welcome to Amoud Universty",R.drawable.bus3);
        file_maps.put("AMOUD UNIVERSITY",R.drawable.bus5);
        file_maps.put("Big stage Of Amoud University",R.drawable.bus6);
        file_maps.put("Amoud University",R.drawable.bus7);
        file_maps.put("Libarary of AMOUD UNIVERSITY",R.drawable.buss8);
        file_maps.put("Libarary of Amoud University",R.drawable.buss9);



        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name));


            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderShow.addSlider(textSliderView);

        }

        ImageView imageView1 = (ImageView) view.findViewById(R.id.imageWeb);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new WebFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

                BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_nav);
                bottomNav.getMenu().getItem(2).setCheckable(true);
            }
        });

        ImageView imageView2 = (ImageView) view.findViewById(R.id.imageResults);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ExamFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

                BottomNavigationView bottomNav = getActivity().findViewById(R.id.bottom_nav);
                bottomNav.getMenu().getItem(1).setCheckable(true);
            }
        });


        return view;

    }
    @Override
    public void onStop() {

        super.onStop();
    }

}
