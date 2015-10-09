package com.application.pptLoader;

import android.content.res.Resources;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Notes;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.io.IOException;

/**
 * Created by Alex on 10/5/2015.
 */
public class PowerpointLoader {

    private SlideShow ppt;

    public void setPpt( SlideShow ppt) {
        this.ppt = ppt;
    }

    public SlideShow openSlideShow( String filePath ){
        SlideShow ppt = null;
        try{
            ppt = new SlideShow(new HSLFSlideShow( filePath ));
            setPpt(ppt);

        } catch (IOException e){
            e.printStackTrace();
        }

        return ppt;
    }

    public Notes[] getNotes( SlideShow ppt ){
        return ppt.getNotes();
    }

    public TextRun[] getNotesTextArray( SlideShow ppt, int slideNumber){
        return getNotes(ppt)[slideNumber].getTextRuns();
    }

    public String getNotesString( SlideShow ppt, int slideNumber ){
        return getNotesTextArray(ppt, slideNumber)[0].getText();
    }

    public void openResource(){
        Resources res = Resources.getSystem();
        SlideShow ppt = null;
        try {
            ppt = new SlideShow(new HSLFSlideShow( res.getAssets().open("slideshow1.ppt") ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
