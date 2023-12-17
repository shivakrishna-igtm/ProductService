package com.example.productservice.Service;

import com.example.productservice.Dtos.FakeApiProductDto;
import com.example.productservice.Dtos.GeneralProductDto;
import com.example.productservice.Exceptions.ProductNotFound;
import com.example.productservice.ThirdPartyClients.FakeStoreClient.FakeClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProductImplementation")
public class FakeProductImpl implements ProductService{

//    private RestTemplateBuilder restTemplateBuilder;
//    String productUrl="https://fakestoreapi.com/products/{id}";
//    String allProductUrl="https://fakestoreapi.com/products";
    private FakeClient fakeAdapter;
    FakeProductImpl(FakeClient fakeAdapter){
        this.fakeAdapter=fakeAdapter;
        /*this.restTemplateBuilder=restTemplateBuilder;*/
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
    public GeneralProductDto getProductById(Long id) throws ProductNotFound {
        //integrating with the external api
        //rest template is a spring class used to do http calls to external apis
        //FakeApiProductDto dto=new FakeApiProductDto();
      /*  RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeApiProductDto> response=restTemplate.getForEntity(productUrl, FakeApiProductDto.class,id);
        if(response.getBody()==null){
            throw new ProductNotFound("Product with id "+id+" not found");
        }*/
        return convertToGeneral(fakeAdapter.getProductById(id));
    }

    @Override
    public List<GeneralProductDto> getAllProducts() {
//       RestTemplate restTemplate=restTemplateBuilder.build();
        List<FakeApiProductDto> productsList;
        List<GeneralProductDto> result=new ArrayList<>();
        //ResponseEntity<FakeApiProductDto[]> response=restTemplate.getForEntity(allProductUrl, FakeApiProductDto[].class);
        productsList=fakeAdapter.getAllProducts();

        for(FakeApiProductDto fakeApiProductDto : productsList){
            result.add(convertToGeneral(fakeApiProductDto));
        }
        return result;
    }

    @Override
    public GeneralProductDto createProduct(GeneralProductDto generalProductDto) {
        /*RestTemplate restTemplate=restTemplateBuilder.build();*/
        /*ResponseEntity<FakeApiProductDto> response= restTemplate.postForEntity(allProductUrl,generalProductDto,FakeApiProductDto.class);*/
        return convertToGeneral(fakeAdapter.createProduct(generalProductDto));
    }

    @Override
    public GeneralProductDto deleteProduct(Long id) {
        //RestTemplate restTemplate=restTemplateBuilder.build();
        //ResponseEntity<GeneralProductDto> response=restTemplate.getEnti
        /*RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeApiProductDto.class);
        ResponseExtractor<ResponseEntity<FakeApiProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeApiProductDto.class);
        ResponseEntity<FakeApiProductDto> response= restTemplate.execute(productUrl, HttpMethod.DELETE, requestCallback, responseExtractor,id);
        */
        return convertToGeneral(fakeAdapter.deleteProduct(id));
    }

    @Override
    public GeneralProductDto updateProduct(Long id,GeneralProductDto generalProductDto) {
        //RestTemplate restTemplate=restTemplateBuilder.build();
        //FakeApiProductDto response=restTemplate.patchForObject(productUrl,generalProductDto,FakeApiProductDto.class,id);
        //return convertToGeneral(response);
        return null;
    }
}
