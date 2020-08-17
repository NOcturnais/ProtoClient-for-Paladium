package fr.noctu.haxx.proto.utils.font;

public class FontManager {

    public TTFRenderer titleFont;
    public TTFRenderer tabGuiFont;
    public TTFRenderer pimageDesc;
    public TTFRenderer arial;
    public TTFRenderer youSelected;

    public void onStart() {
        titleFont = new TTFRenderer("Rubik", 30);
        tabGuiFont = new TTFRenderer("Carlito", 20);
        pimageDesc = new TTFRenderer("Carlito", 10);
        arial = new TTFRenderer("Arial", 30);
        youSelected = new TTFRenderer("Franklin Gothic", 20);
    }

    public TTFRenderer getTitleFont() {
        return titleFont;
    }
}