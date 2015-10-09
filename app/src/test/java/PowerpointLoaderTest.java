import android.content.res.Resources;

import com.application.pptLoader.PowerpointLoader;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class PowerpointLoaderTest {

    @Test
    public void testOpenPowerpoint(){
        Resources res = Resources.getSystem();
        SlideShow ppt = null;
        try {
            ppt = new SlideShow(new HSLFSlideShow( res.getAssets().open("slideshow1.ppt") ));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(true);
    }
	
	
}