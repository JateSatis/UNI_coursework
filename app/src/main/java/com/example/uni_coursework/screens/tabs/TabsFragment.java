package com.example.uni_coursework.screens.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import com.example.uni_coursework.R;
import com.example.uni_coursework.databinding.FragmentTabsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class TabsFragment extends Fragment {
    private FragmentTabsBinding binding;
    private NavController navController;
    private int currentTab = R.id.search_menu_tab;
    private Map<Integer, Integer> tabsCurrentFragmentMap = new HashMap<Integer, Integer>() {{
        put(R.id.search_menu_tab, R.id.searchFragment);
        put(R.id.bookings_menu_tab, R.id.bookingsFragment);
    }};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabsBinding.inflate(inflater, container, false);

        NavHostFragment navHost = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.tabsGraphContainer);
        navController = navHost.getNavController();

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navController.navigateUp();
            }
        });

        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                navigateToTab(item.getItemId());
                return true;
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                int newTab = getTabFromDestination(destination.getId());
                tabsCurrentFragmentMap.put(newTab, destination.getId());
                updateBottomNavigationView(newTab);
            }
        });

        return binding.getRoot();
    }

    private void navigateToTab(int tabId) {
        navController.navigate(tabsCurrentFragmentMap.getOrDefault(tabId, R.id.searchFragment));
    }

    private void updateBottomNavigationView(int newTab) {
        if (newTab != currentTab) {
            binding.bottomNavigationView.getMenu().findItem(currentTab).setChecked(false);
            binding.bottomNavigationView.getMenu().findItem(newTab).setChecked(true);
            currentTab = newTab;
        }
    }

    private int getTabFromDestination(int destinationId) {
        if (destinationId == R.id.searchFragment || destinationId == R.id.searchFragment) {
                return R.id.search_menu_tab;
        } else if (destinationId == R.id.bookingsFragment || destinationId == R.id.addRoomFragment) {
            return R.id.bookings_menu_tab;
        } else {
            return R.id.search_menu_tab;
        }
    }
}