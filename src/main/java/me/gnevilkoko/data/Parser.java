package me.gnevilkoko.data;

import me.gnevilkoko.models.ModelBase;

public interface Parser {
    ModelBase parse(Reader reader);
}
