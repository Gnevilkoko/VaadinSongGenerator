package me.gnevilkoko.viewmodels;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.BeforeEnterEvent;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.LanguageFileModel;
import me.gnevilkoko.models.yaml.YamlModel;
import me.gnevilkoko.views.SongSettingsView;

public class SongSettingsViewModel extends ViewModelBase {
    private LanguageFileModel languageFileModel;
    private SongSettingsView view;
    private String title;

    public SongSettingsViewModel(SongSettingsView view) {
        this.view = view;

        YamlModel yamlModel = new YamlParser().parse(new YamlReader("language.yml"));
        languageFileModel = new LanguageFileModel(yamlModel);
    }

    @Override
    public String getPageTitle() {
        return title;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {


        super.beforeEnter(event);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        loadLanguageText();

        super.afterNavigation(event);
    }

    @Override
    public void loadLanguageText() {
        title = languageFileModel.getAppName();
    }
}
