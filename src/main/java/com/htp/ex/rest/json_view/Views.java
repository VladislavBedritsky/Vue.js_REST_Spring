package com.htp.ex.rest.json_view;

public final class Views {

    public interface Id {}

    public interface IdAndText extends Id {}

    public interface MessageWithoutText extends Id {}

    public interface FullMessage extends IdAndText {}

}
