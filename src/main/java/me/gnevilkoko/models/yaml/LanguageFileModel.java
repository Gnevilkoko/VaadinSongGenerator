package me.gnevilkoko.models.yaml;

import java.util.Map;

public class LanguageFileModel extends YamlModel {
    public LanguageFileModel(Map<String, Object> data) {
        super(data);
    }

    public LanguageFileModel(YamlModel yamlModel) {
        super(yamlModel);
    }

    public String getAppName(){
        return (String) getData().get(Names.APP_NAME.getKey());
    }

    public String getNicknameLabel(){
        return (String) getData().get(Names.NICKNAME.getKey());
    }
    public String getSongAboutTabName() { return (String) getData().get(Names.SONG_ABOUT_TAB.getKey()); }
    public String getSongTextTabName() { return (String) getData().get(Names.SONG_TEXT_TAB.getKey()); }
    public String getEnter() { return (String) getData().get(Names.ENTER.getKey()); }
    public String getGenerateButtonLabel(){
        return (String) getData().get(Names.GENERATE_BUTTON.getKey());
    }
    public String getSongStyle(){
        return (String) getData().get(Names.SONG_STYLE.getKey());
    }

    public String getExampleStyle(){
        return (String) getData().get(Names.EXAMPLE_STYLE.getKey());
    }

    public String getExampleSongAbout(){
        return (String) getData().get(Names.EXAMPLE_SONG_ABOUT.getKey());
    }

    public String getExampleSongText(){
        return (String) getData().get(Names.EXAMPLE_SONG_TEXT.getKey());
    }

    public String getSongsOrderButton(){
        return (String) getData().get(Names.SONGS_ORDER_BUTTON.getKey());
    }

    public String addEnter(String source){
        return getEnter()+" "+source.toLowerCase();
    }

    public enum Names {
        APP_NAME("appname"),
        NICKNAME("nickname"),
        SONG_ABOUT_TAB("song_about_tab"),
        SONG_TEXT_TAB("song_text_tab"),
        ENTER("enter"),
        GENERATE_BUTTON("generate_button"),
        SONGS_ORDER_BUTTON("songs_order_button"),
        SONG_STYLE("song_style"),
        EXAMPLE_STYLE("example_style"),
        EXAMPLE_SONG_ABOUT("example_song_about"),
        EXAMPLE_SONG_TEXT("example_song_text");

        private String key;

        Names(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        @Override
        public String toString() {
            return getKey();
        }
    }
}
