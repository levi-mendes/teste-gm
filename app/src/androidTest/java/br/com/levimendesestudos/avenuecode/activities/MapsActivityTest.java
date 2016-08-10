package br.com.levimendesestudos.avenuecode.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;

import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.activities.MainActivity;
import br.com.levimendesestudos.avenuecode.activities.MapsActivity;
import br.com.levimendesestudos.avenuecode.models.Address;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by 809778 on 10/08/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MapsActivityTest {

    @Rule
    public ActivityTestRule<MapsActivity> rule = new ActivityTestRule<MapsActivity>(MapsActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(targetContext, MainActivity.class);

            Address address = new Address();

            //Address: "Caieiras, SP, Brazil"  Coordinates>> lat: -23.3612198 long: -46.7401869

            address.formattedAddress = "Caieiras, SP, Brazil";
            address.lati             = -23.3612198;
            address.longi            = -46.7401869;

            List<Address> list = new ArrayList<>();

            list.add(address);

            Bundle bundle = new Bundle();

            //Intent intent = new Intent();
            bundle.putBoolean("all", false);
            bundle.putSerializable("addresses", (ArrayList)list);
            bundle.putInt("position", 0);

            intent.putExtras(bundle);

            return intent;
        }
    };

    @Test
    public void shouldShowSaveItemSave() {
        onView(ViewMatchers.withId(R.id.itemSave)).check(matches(isDisplayed()));
    }
}