# Cart Budy API

**Problem:**

People often shop without knowing how much they've spent until they reach the checkout

**Solution:**

An app where you can set a budget, build your visual cart while shoopping, and instantly see live of how much you've spent and how much money remains.

**Features**
1. Set a budget
   Example:
     name: 1 Week grocery budget
     amount: 3,000
2. Add items in the cart
   Manual Input
     product name: 1 dozen Eggs
     unit price: P 123
     quantity: 2
     category: (automatically identified, or user can set on their own)
     source: SCANNED or MANUAL
   Scan Product Tag
     image: photo of product tag
 3. Live cart calculation
      unit price of product: unit price of product
      quantity x price: unit price of each product multiplied in the quantity entered
      estimatated total:
      remaining budget: budget subtracted to estimated total
4. Budget indicator
     status: safe, getting close or over budget
5. Shopping summary
     total spent:
     number of products:
     most expensive product:
     spending category:

Sample UX

<img width="286" height="471" alt="image" src="https://github.com/user-attachments/assets/825f49ad-41b6-44d0-b752-f0502b368549" />

**MODELS**

Budget
- id
- shoppingSessionId
- name
- amount
- currencyCode
- status
- createdAt
- updatedAt

ShoppingSession
- id
- budgetId
- status
- createdAt
- updatedAt

ShopppingItem
- id
- productId
- quantity
- source
- createdAt
- updatedAt

Product
- id
- name
- brand
- category
- imageUrl
- createdAt
- updatedAt

OcrScan
- id
- imageId
- status
- rawText
- detectedProductName
- detectedPrice
- detectedCurrency
- confidence
- createdAt
- completedAt




    
    
    
     
