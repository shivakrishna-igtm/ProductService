package com.example.productservice.ThirdPartyClients.FakeStoreClient;

import com.example.productservice.Dtos.FakeApiProductDto;
import com.example.productservice.Dtos.GeneralProductDto;
import com.example.productservice.Exceptions.ProductNotFound;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Component
public class FakeClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String productUrl;
    private String allProductUrl;
    public FakeClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakestore.api.url}") String productUrl,
                      @Value("${fakestore.api.paths.products}") String allProductUrl) {
        this.restTemplateBuilder=restTemplateBuilder;
        this.productUrl=productUrl+allProductUrl+"/{id}";
        this.allProductUrl=productUrl+allProductUrl;

    }
   /* private GeneralProductDto convertToGeneral(FakeApiProductDto fakeApiProductDto){
        GeneralProductDto dto=new GeneralProductDto();
        dto.setCategory(fakeApiProductDto.getCategory());
        dto.setId(fakeApiProductDto.getId());
        dto.setTitle(fakeApiProductDto.getTitle());
        dto.setPrice(fakeApiProductDto.getPrice());
        dto.setDescription(fakeApiProductDto.getDescription());
        dto.setImage(fakeApiProductDto.getImage());
        return dto;
    }*/

    public FakeApiProductDto getProductById(Long id) throws ProductNotFound {
        //integrating with the external api
        //rest template is a spring class used to do http calls to external apis
        //FakeApiProductDto dto=new FakeApiProductDto();
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeApiProductDto> response=restTemplate.getForEntity(productUrl, FakeApiProductDto.class,id);
        if(response.getBody()==null){
            throw new ProductNotFound("Product with id "+id+" not found");
        }
        return response.getBody();
    }


    public List<FakeApiProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        List<FakeApiProductDto> productsList;
        //List<GeneralProductDto> result=new ArrayList<>();
        ResponseEntity<FakeApiProductDto[]> response=restTemplate.getForEntity(allProductUrl, FakeApiProductDto[].class);
        productsList=List.of(response.getBody());

        //for(FakeApiProductDto fakeApiProductDto : productsList){
        //    result.add(convertToGeneral(fakeApiProductDto));
        //}
        return productsList;
    }


    public FakeApiProductDto createProduct(GeneralProductDto generalProductDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeApiProductDto> response= restTemplate.postForEntity(allProductUrl,generalProductDto,FakeApiProductDto.class);
        return (response.getBody());
    }
    public FakeApiProductDto deleteProduct(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //ResponseEntity<GeneralProductDto> response=restTemplate.getEnti
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeApiProductDto.class);
        ResponseExtractor<ResponseEntity<FakeApiProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeApiProductDto.class);
        ResponseEntity<FakeApiProductDto> response= restTemplate.execute(productUrl, HttpMethod.DELETE, requestCallback, responseExtractor,id);
        return (response.getBody());
    }


    public GeneralProductDto updateProduct(Long id,GeneralProductDto generalProductDto) {
        /*RestTemplate restTemplate=restTemplateBuilder.build();
        FakeApiProductDto response=restTemplate.patchForObject(productUrl,generalProductDto,FakeApiProductDto.class,id);
        return (response);*/
        return null;
    }
}
