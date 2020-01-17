package com.htp.ex.service;

import com.htp.ex.dto.MetaDto;
import com.htp.ex.model.Message;
import org.jsoup.nodes.Element;

import java.io.IOException;

public interface MetaDtoService {

    void fillMeta (Message message) throws IOException;

    MetaDto getMeta (String url) throws IOException;

    String getContent(Element element);
}
