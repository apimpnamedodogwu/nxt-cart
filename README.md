## This paper describes the architecture of an e-commerce feature: a Java and Spring Boot-based customisable coupon system. The main idea is to allow other discount techniques to be added without needing major code changes later on.
## System Architecture
### Backend (Spring Boot):

- API Layer: Provides endpoints for cart and coupon functionalities.
- Service Layer: Business logic for coupon validation, discount application, and data retrieval.
- Repository Layer: Interacts with the database to access and manage coupon data (rules, discounts).
- Database: Stores coupon information, rules, and discount types.

The database design includes the following tables:

### Coupon
### Rules
### Discount

## Code Organisation:
### Package Structure:
#### - config
#### - controllers
#### - data
#### - services

### config:
This class is used to populate the database with initial data.

### controllers:
CartController - Handles cart-related requests ( /cart)

CouponController - Handles coupon application logic ( /coupon)

### data:
Compromises of dto, models, and repositories.

dto -> Cart, Item, and Coupon Response object.

models -> enum directory, Coupon, Rules, and Discount entities.

repositories -> provides CRUD for Coupon, Rules, and Discount type data.

### services:
cart -> populates the cart object with default data and functionalities to perform the summation and retrieval of the cart.

coupon -> validates the coupons and rules, applies discount, and retrieves relevant data.

