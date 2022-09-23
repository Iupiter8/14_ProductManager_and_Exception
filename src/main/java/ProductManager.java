public class ProductManager {

    private ProductRepository repo;

    public ProductManager(ProductRepository repo) {
        this.repo = repo;
    }

    public Product[] add(Product product) {
        repo.save(product);
        return repo.findAll();
    }


    public Product[] searchBy(String text) {
        Product[] result = new Product[0]; //тут сохранятся подощедшие по запросу продукты
        for (Product product: repo.findAll()) {
            if (matches(product, text)) {
                //добавляем в конец массива result продукт product
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
//                for (int i = 0; i < result.length; i++) {
//                    tmp[i] = result[i];
//                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }

    // метод определения соответствия товара product запросу search

    public boolean matches(Product product, String search) {

        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }


        // можно в одну строку:
        // return product.getName.contains(search));
    }

}
