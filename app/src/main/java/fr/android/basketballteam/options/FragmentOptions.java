package fr.android.basketballteam.options;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import fr.android.basketballteam.R;

public class FragmentOptions extends Fragment {

    private OptionsFragListener activityMain;
    private Spinner spinnerLanguages;
    private boolean isInit;

    /** Constructor */
    public FragmentOptions(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        isInit = true;
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_options, container, false);
        this.spinnerLanguages = view.findViewById(R.id.spinner_language);

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String language = sharedpreferences.getString("language", "English");

        ArrayList<String> languages = new ArrayList<>();
        if(language.equals("English")){
            languages.add("English");
            languages.add("Français");
        }else{
            languages.add("Français");
            languages.add("English");
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, languages);
        // Drop down layout style – list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerLanguages.setAdapter(dataAdapter);

        spinnerLanguages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isInit){
                    isInit = false;
                }else{
                    // On selecting a spinner item
                    String language = parent.getItemAtPosition(position).toString();
                    activityMain.onLanguageSelected(language);
                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("language", language);
                    editor.commit();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // View Creation
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OptionsFragListener) {
            activityMain = (OptionsFragListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OptionsFragListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activityMain = null;
    }
}
