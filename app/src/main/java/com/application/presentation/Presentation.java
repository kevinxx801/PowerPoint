package com.application.presentation;

import com.application.pptLoader.PowerpointLoader;
import com.application.timer.Timer;

import org.apache.poi.hslf.usermodel.SlideShow;

import java.util.ArrayList;

public class Presentation {
    ArrayList<Slide> slides;
    Slide currentSlide;
    Timer PresentationTimer;
    int slideCount;

    public Presentation() {
        //Not sure how we want to handle this, depends on how we get data
        this.slides = new ArrayList<>();
        this.slideCount = 0;
    }

    public void loadPowerpointFromPath( String filePath ){
        PowerpointLoader pptLoader = new PowerpointLoader();
        SlideShow ppt = pptLoader.openSlideShow(filePath);
        loadPowerpoint(ppt);
    }

    public void loadPowerpoint( SlideShow ppt ){
        PowerpointLoader pptLoader = new PowerpointLoader(ppt);
        String notesString = "";
        slideCount = pptLoader.getSlideCount(ppt);

        for (int i = 0; i < pptLoader.getSlideCount(ppt); i++ ){
            notesString = pptLoader.getNotesString(i);
            Note note = new Note();
            note.setMessage(notesString);
            Slide slide = new Slide();
            slide.setNote(note);
            addSlide(slide);
        }
        this.currentSlide = slides.get(0);
    }

    public void addSlide(Slide slide) {
        slides.add(slide);
    }

    public void nextSlide() {
        if (slides.indexOf(currentSlide) < slides.size() - 1) {
            int nextSlide = slides.indexOf(currentSlide) + 1;
            currentSlide = slides.get(nextSlide);
        }
    }

    public void previousSlide() {
        if (slides.indexOf(currentSlide) > 0) {
            int previousSlide = slides.indexOf(currentSlide) - 1;
            currentSlide = slides.get(previousSlide);
        }
    }

    public Slide getCurrentSlide() {
        return currentSlide;
    }

    public int getCurrentSlideIndex() {
        return slides.indexOf(currentSlide)+1;
    }

    public int getTotalSlideCount() {
        return slides.size();
    }

    public int getSlideCount(){
        return this.slideCount;
    }

}

