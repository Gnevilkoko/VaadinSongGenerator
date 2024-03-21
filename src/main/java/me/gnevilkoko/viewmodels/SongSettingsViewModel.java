package me.gnevilkoko.viewmodels;

import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.LanguageFileModel;
import me.gnevilkoko.views.SongSettingsView;

public class SongSettingsViewModel extends ViewModelBase {
    private LanguageFileModel languageFileModel;
    private SongSettingsView view;

    public SongSettingsViewModel(SongSettingsView view) {
        this.view = view;
        languageFileModel = (LanguageFileModel) new YamlParser().parse(new YamlReader("language.yml"));
    }
    
    @Override
    public void loadLanguageText() {

    }
}
