# Tìm hiểu về Spring Data Specifications

Nếu bạn đang tìm kiếm một cách tốt hơn để quản lý các truy vấn của mình hoặc muốn tạo các truy vấn động và an toàn kiểu chữ thì bạn có thể tìm thấy giải pháp của mình trong Thông số kỹ thuật JPA của Spring Data.

## Vậy thì Specifications là gì?

Spring Data JPA Specifications là một công cụ khác mà chúng tôi sử dụng để thực hiện các truy vấn cơ sở dữ liệu với Spring hoặc Spring Boot.
Specifications được xây dựng dựa trên Criteria API.
Khi tạo một truy vấn Criteria chúng ta bắt buộc phải tụ xây dựng và quản lý các đối tượng `Root`, `CriteraQuery`, và `CriteriaBuilder`:

```java
...
EntityManager entityManagr = getEntityManager();

CriteriaBuilder builder = entityManager.getCriteriaBuilder();

CriteriaQuery<Product> productQuery = builder.createQuery(Product.class);

Root<Person> personRoot = productQuery.from(Product.class);
...
```

Specifications được xây dựng dựa trên Criteria API để đơn giản hóa các thao tác của lập trình viên. Đơn giản là chúng ta chỉ cần triển khai `Specification interface`:

```java
interface Specification<T>{

  Predicate toPredicate(Root<T> root,
            CriteriaQuery<?> query,
            CriteriaBuilder criteriaBuilder);

}
```
