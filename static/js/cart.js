"use strict";

// Provide the URL for the API path that will retrieve the products. 
var registerApi = '//localhost:8080/api/register';
var salesApi = '//localhost:8080/api/sales';
var productApi = '//localhost:8080/api/product';

class SaleItem {
    constructor(product, quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = product.listPrice;
    }
}

class Sale {
    constructor(customer, items) {
        this.customer = customer;
        this.items = items;
    }
}

const app = Vue.createApp({
    
    mixins:[NumberFormatter],

    data() {
        return {
            // models (comma separated key/value pairs)

        };
    },

    computed: Vuex.mapState({
        product: 'selectedProduct',
        items: 'items',
        customer: 'customer'
    }),


    mounted() {
        // semicolon separated statements
        //alert('Mounted method called');


    },

    methods: {
        // comma separated function declarations
        // add item to cart
        addToCart(){
            dataStore.commit("addItem", new SaleItem(this.product, this.quantityPurchased));
            window.location = "cart.html";
        },
//        addItem(state, item) {
//            //state.items.push(item);
//            //
//            alert("Add Item" + this.item );
//            axios.post(itemApi, this.item)
//                    .then(()=> {
//                        state.items.push(item);
//                        dataStore.commit("addItem", new SaleItem(this.product, this.quantity));
//                    })
//                    .catch(error => {
//                    console.error(error);
//                    alert("An error occurred during addItem- check the console for details.");
//                });
//            
//        },
        checkOut() {
            // Sale Domain Object
            let sale = new Sale(this.customer, this.items);

            axios.post(salesApi, this.sale)
                    .then(()=> {
                        dataStore.commit("clearItems(state)");
                        window.location = 'thankyou.html';
                        
                    })
                    .catch(error => {
                        console.error(error);
                        alert("An error occurred during 'checkOut()'.")
                    });
                    
        },
        getItemTotal(item){
            return item.salePrice * selectedProduct.quantityPurchased;
        },
        getGrandTotal(){
            let total = 0;
            return total;
            
        },
        // clear cart items
        clearItems(state) {
            state.items = new Array();
        }
        
    }

});

/* other component imports go here */

// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);  

import { NumberFormatter } from './numberFormatter.js';

// import navigation  menu component
import { NavigationMenu } from './navigation.js';
app.component('navigation', NavigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("#content");