package com.example.gonza.probando;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * Created by gonza on 12/03/2018.
 */

public class GridFragment extends Fragment{
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static GridFragment newInstance(int sectionNumber){
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        // Obtencion del grid view
        GridViewWithHeaderAndFooter grid = (GridViewWithHeaderAndFooter) rootView.findViewById(R.id.gridview);

        // Inicializar el grid view
        setUpGridView(grid);

        return rootView;
    }

    private void setUpGridView(GridViewWithHeaderAndFooter grid){

    }
}
