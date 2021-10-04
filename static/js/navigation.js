"use strict";

// import data store
import { dataStore } from './data-store.js'

export const NavigationMenu = {

    computed: {
        signedIn() {
            return this.customer != null;
        },
        ...Vuex.mapState({
            customer: 'customer'
        })
    },

    template:
    `
    <nav>
        <div v-if="signedIn">Welcome {{customer.firstName}}</div>
        <a href=".">Home</a>
        <a href="products.html" v-if="signedIn">Browse Products</a>
        <a href="cart.html" v-if="signedIn">View Cart</a>
        <a href="#" v-if="signedIn" @click="signOut()">Sign Out</a>
        <a href="signin.html" v-if="!signedIn">Sign In</a>
    </nav>
    `,

    methods:{
        signOut() {
            sessionStorage.clear();
            window.location = '.';
        }
    }
};  