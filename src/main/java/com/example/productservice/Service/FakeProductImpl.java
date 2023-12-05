package com.example.productservice.Service;

import com.example.productservice.Dtos.GeneralProductDto;
import com.example.productservice.Dtos.FakeApiProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;
    String productUrl="https://fakestoreapi.com/products/{id}";
    String allProductUrl="https://fakestoreapi.com/products";
    FakeProductImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    private GeneralProductDto convertToGeneral(FakeApiProductDto fakeApiProductDto){
        GeneralProductDto dto=new GeneralProductDto();
        dto.setCategory(fakeApiProductDto.getCategory());
        dto.setId(fakeApiProductDto.getId());
        dto.setTitle(fakeApiProductDto.getTitle());
        dto.setPrice(fakeApiProductDto.getPrice());
        dto.setDescription(fakeApiProductDto.getDescription());
        dto.setImage(fakeApiProductDto.getImage());
        return dto;
    }
    @Override
    public GeneralProductDto getProductById(Long id) {
        //integrating with the external api
        //rest template is a spring class used to do http calls to external apis
        //FakeApiProductDto dto=new FakeApiProductDto();
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeApiProductDto> response=restTemplate.getForEntity(productUrl, FakeApiProductDto.class,id);
        return convertToGeneral(response.getBody());
    }

    @Override
    public List<GeneralProductDto> getAllProducts() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        List<FakeApiProductDto> productsList;
        List<GeneralProductDto> result=new ArrayList<>();
        ResponseEntity<FakeApiProductDto[]> response=restTemplate.getForEntity(allProductUrl, FakeApiProductDto[].class);
        productsList=List.of(response.getBody());

        for(FakeApiProductDto fakeApiProductDto : productsList){
            result.add(convertToGeneral(fakeApiProductDto));
        }
        return result;
    }

    @Override
    public GeneralProductDto createProduct(GeneralProductDto generalProductDto) {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeApiProductDto> response= restTemplate.postForEntity(allProductUrl,generalProductDto,FakeApiProductDto.class);
        return convertToGeneral(response.getBody());
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public void updateProduct(Long id) {

    }
}
