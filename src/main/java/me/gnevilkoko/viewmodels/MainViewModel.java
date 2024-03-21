package me.gnevilkoko.viewmodels;

import com.vaadin.flow.router.AfterNavigationEvent;
import me.gnevilkoko.data.YamlParser;
import me.gnevilkoko.data.YamlReader;
import me.gnevilkoko.models.yaml.LanguageFileModel;
import me.gnevilkoko.views.MainLayout;
import me.gnevilkoko.views.SongSettingsView;

public class MainViewModel extends ViewModelBase {
    private LanguageFileModel languageFileModel;
    private MainLayout view;

    public MainViewModel(MainLayout view) {
        this.view = view;
        languageFileModel = (LanguageFileModel) new YamlParser().parse(new YamlReader("language.yml"));
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        loadLanguageText();

        super.afterNavigation(event);
    }

    @Override
    public void loadLanguageText() {
        view.getViewTitle().setText(languageFileModel.getAppName());
        System.out.println("WORK: "+languageFileModel.getAppName());
    }
}
