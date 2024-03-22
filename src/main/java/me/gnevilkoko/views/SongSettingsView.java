package me.gnevilkoko.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;
import me.gnevilkoko.viewmodels.SongSettingsViewModel;

@Route(value = "app", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class SongSettingsView extends HorizontalLayout implements AfterNavigationObserver,
                                                                    BeforeEnterObserver,
                                                                    HasDynamicTitle {
    private SongSettingsViewModel model;
    public SongSettingsView() {
        this.model = new SongSettingsViewModel(this);

        setSizeFull();
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        model.afterNavigation(afterNavigationEvent);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        model.beforeEnter(beforeEnterEvent);
    }

    @Override
    public String getPageTitle() {
        return model.getPageTitle();
    }
}
