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

    public PowerpointLoader(){
        this.ppt = null;
    }

    public PowerpointLoader(SlideShow slideShow){
        this.ppt = slideShow;
    }

    public void setPpt( SlideShow ppt) {
        this.ppt = ppt;
    }

    public int getSlideCount( SlideShow ppt ){
        return ppt.getNotes().length;
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

    public String getNotesString( int slideNumber ){
        return getNotesTextArray(this.ppt, slideNumber)[0].getText();
    }

}
