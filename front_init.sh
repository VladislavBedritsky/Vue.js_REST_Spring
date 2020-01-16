
# chmod +x ./front_init.sh && ./front_init.sh

yarn init

### vue dependency
yarn add vue vue-resource

### devDependencies webpack, babel, vue
yarn add -D webpack webpack-cli webpack-dev-server babel-loader @babel/core @babel/preset-env vue-loader vue-template-compiler

### websocket dependency
yarn add sockjs-client @stomp/stompjs

### devDependencies vue-style, css
yarn add -D vue-style-loader css-loader

### vuetify dependency
yarn add vuetify

### vuex dependency
yarn add vuex