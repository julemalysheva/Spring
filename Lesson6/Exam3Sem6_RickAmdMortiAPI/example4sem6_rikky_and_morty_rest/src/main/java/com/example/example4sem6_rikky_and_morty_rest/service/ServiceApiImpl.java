package com.example.example4sem6_rikky_and_morty_rest.service;

import com.example.example4sem6_rikky_and_morty_rest.domain.Characters;
import com.example.example4sem6_rikky_and_morty_rest.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Реализация интерфейса ServiceApi для работы с API сервисом "Rick and Morty".
 */
@Service
public class ServiceApiImpl implements ServiceApi{

    @Autowired
    private RestTemplate template;

    @Autowired
    private HttpHeaders headers;

    @Value("${external.api.url}")
    private String apiUrl;

    @Value("${pagination.perPage}")
    private int paginationPerPage;

    /**
     * Получает все персонажи.
     * @return Коллекция персонажей
     */
    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(apiUrl, HttpMethod.GET,entity, Characters.class);

        return responce.getBody();
    }

    /**
     * Получает информацию о персонаже по его идентификатору.
     * @param id Идентификатор персонажа
     * @return Информация о персонаже
     */
    @Override
    public Result getCharacterById(Integer id) {
        String urlPathId = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .pathSegment(id.toString())
                .toUriString();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Result> response = template.exchange(urlPathId, HttpMethod.GET, entity, Result.class);
        return response.getBody();
    }

    /**
     * Получает коллекцию персонажей с определенной страницы.
     * @param page Номер страницы
     * @return Коллекция персонажей с указанной страницы
     */
    @Override
    public Characters getCharactersWithPage(String page) {
        String charactersUrlWithPage = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("page", page)
                .toUriString();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(charactersUrlWithPage, HttpMethod.GET,entity, Characters.class);

        return responce.getBody();
    }

    /**
     * Получает номер страницы, на которой находится персонаж с указанным идентификатором.
     * @param id Идентификатор персонажа
     * @return Номер страницы, на которой находится персонаж
     */
    public int getPageNumberById(int id) {
        return (id - 1) / paginationPerPage + 1;
    }


}
