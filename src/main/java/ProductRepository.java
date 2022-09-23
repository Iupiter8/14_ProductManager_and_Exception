public class ProductRepository {


    private Product[] products = new Product[0];

    public void save(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException("Позиция с ID " + product.id + " уже существует");
        } else {

            Product[] tmp = new Product[products.length + 1];
            for (int i = 0; i < products.length; i++) {
                tmp[i] = products[i];
            }
            tmp[tmp.length - 1] = product;
            products = tmp;
        }
    }


    public void removeById(int id) {

        if (findById(id) != null) {

            Product[] tpm = new Product[products.length - 1];
            int copyToIndex = 0;
            for (Product product : products) {
                if (product.getId() != id) {
                    tpm[copyToIndex] = product;
                    copyToIndex++;
                }
            }
            products = tpm;
        } else {
            throw new NotFoundException("Позиция с ID " + id + " не найдена");
        }
    }


    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }


    public Product[] findAll(){
        return products;
    }


}
