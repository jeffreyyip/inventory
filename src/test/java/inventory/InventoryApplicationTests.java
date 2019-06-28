package inventory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class InventoryApplicationTests {



	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		productRepository.deleteAll();
	}


	@Test
	public void shouldCreateEntity() throws Exception {

		Product product = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SHOES);

		mockMvc.perform(post("/product").content(
				toJson(product))).andExpect(
				status().isCreated()).andExpect(
				header().string("Location", containsString("product/")));


	}

	@Test
	public void shouldRetrieveEntity() throws Exception {

		Product product = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SHOES);


		MvcResult mvcResult = mockMvc.perform(post("/product").content(
				toJson(product))).andExpect(
				status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.name").value(product.getName())).andExpect(
				jsonPath("$.quantity").value(product.getQuantity())).andExpect(
				jsonPath("$.category").value(product.getCategory().name())).andExpect(
				jsonPath("$.subCategory").value(product.getSubCategory().name()));
	}


	@Test
	public void shouldQueryEntity() throws Exception {

		Product product = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SHOES);

		mockMvc.perform(post("/product").content(
				toJson(product))).andExpect(
				status().isCreated());

		mockMvc.perform(
				get("/product/search/findByName?name={name}", product.getName())).andExpect(
				status().isOk()).andExpect(
				jsonPath("$._embedded.product[0].name").value(
						product.getName()));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {

		Product product = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SHOES);
		Product updatedProduct = new Product("AirJordan"+"X", 100+100, Category.CLOTHES, SubCategory.SHOES);

		MvcResult mvcResult = mockMvc.perform(post("/product").content(
				toJson(product))).andExpect(
				status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				toJson(updatedProduct))).andExpect(
				status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.name").value(updatedProduct.getName())).andExpect(
				jsonPath("$.quantity").value(updatedProduct.getQuantity())).andExpect(
				jsonPath("$.category").value(updatedProduct.getCategory().name())).andExpect(
				jsonPath("$.subCategory").value(updatedProduct.getSubCategory().name()));
	}


	@Test
	public void shouldDeleteEntity() throws Exception {

		Product product = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SHOES);

		MvcResult mvcResult = mockMvc.perform(post("/product").content(
				toJson(product))).andExpect(
				status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}

	@Test
	public void shouldNOTCreateEntity_whenMismatchCategories() throws Exception {

		Product mismatchCategories = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SOFA);

		mockMvc.perform(post("/product").content(
				toJson(mismatchCategories))).andExpect(
				status().isBadRequest());

	}

	@Test
	public void shouldNOTUpdateEntity_whenMismatchCategories() throws Exception {

		Product product = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SHOES);

		Product mismatchCategories = new Product("AirJordan", 100, Category.CLOTHES, SubCategory.SOFA);

		MvcResult mvcResult = mockMvc.perform(post("/product").content(
				toJson(product))).andExpect(
				status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				toJson(mismatchCategories))).andExpect(
				status().isBadRequest());


		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.name").value(product.getName())).andExpect(
				jsonPath("$.quantity").value(product.getQuantity())).andExpect(
				jsonPath("$.category").value(product.getCategory().name())).andExpect(
				jsonPath("$.subCategory").value(product.getSubCategory().name()));

	}


	@Test
	public void shouldNOTCreateEntity_whenWrongCategory() throws Exception {

		String wrongCategory = toJson("AirJordan", 100, "clothes", "SHOES");
		mockMvc.perform(post("/product").content(
				wrongCategory)).andExpect(
				status().isBadRequest());

	}

	@Test
	public void shouldNOTCreateEntity_whenWrongSubcategory() throws Exception {

		String wrongCategory = toJson("AirJordan", 100, "CLOTHES", "shoes");
		mockMvc.perform(post("/product").content(
				wrongCategory)).andExpect(
				status().isBadRequest());

	}

	private String toJson(Product p) {
		return toJson(p.getName(), p.getQuantity(), p.getCategory().name(), p.getSubCategory().name());
	}

	private String toJson(String name, int quantity, String category, String subCategory){
		return new StringBuilder().append("{\"name\":\"").append(name).append("\"")
				.append(",\"quantity\":").append(quantity)
				.append(",\"category\":\"").append(category).append("\"")
				.append(",\"subCategory\":\"").append(subCategory).append("\"")
				.append("}").toString();
	}


}
