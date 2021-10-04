"use strict";

// Provide the URL for the API path that will retrieve the products. 
var productsApi = '//localhost:8080/api/products';
var categoriesApi = '//localhost:8080/api/categories';
var categoriesFilterApi = ({category}) => `//localhost:8080/api/categories/${category}`;

// Create a Vue application:
const app = Vue.createApp({
    
    mixins:[NumberFormatter],
    
    // The data function is where we define the models.
    data() {
        return {
            // models (comma separated key/value pairs)
            products: new Array(),
            categories: new Array()
            
        };
    },
    // The mounted function is effectively a constructor
    mounted() {
        //alert('Mounted method called');
        // semicolon separated statements
        this.getAllProducts();
        this.getAllCategories();
    },
    // the methods field is where we add the method that the component will use 
    // (such as the event handlers for user interface components).
    methods: {
        // comma separated function declarations
        getAllProducts() {

            axios.get(productsApi)
                .then(response => {
                    this.products = response.data;
                })
                .catch(error => {
                    console.error(error);
                    alert("An error occurred during getAllProducts - check the console for details.");
                });
        },
        getAllCategories() {

            axios.get(categoriesApi)
                .then(response => {
                    //alert("Getting Categories");
                    this.categories = response.data;
                })
                .catch(error => {
                    console.error(error);
                    alert("An error occurred during getAllCategories - check the console for details.");
                });
        },
        // click handler for the category filter buttons
        filterByCategory(category) {
            //alert(category + " was selected");
            axios.get(categoriesFilterApi({'category':category}))
               .then(response => {
                   this.products = response.data;
               })
               .catch(error => {
                   console.error(error);
                   alert("An error occurred - check the console for details.");
               });
        },
        buyProduct(product) {
            dataStore.commit("selectProduct", product);
            window.location = "purchase.html"
        }
    }

});

// other component imports go here

// import data store
import { dataStore } from './data-store.js'
app.use(dataStore);

import { NumberFormatter } from './numberFormatter.js';

// import navigation  menu component
import { NavigationMenu } from './navigation.js';
app.component('navigation', NavigationMenu);

// mount the page - this needs to be the last line in the file
app.mount("#content");