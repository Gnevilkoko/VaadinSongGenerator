package me.gnevilkoko.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("My View")
@Route(value = "app", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
public class SongSettingsView extends HorizontalLayout {

    public SongSettingsView() {
        setSizeFull();

    }
}
