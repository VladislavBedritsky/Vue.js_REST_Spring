<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
    <span v-bind:title="qqq">
            Наведи на меня курсор на пару секунд,
                чтобы увидеть динамически связанное значение title!
    </span>
</template>

<script>
    export default {
          data: function() {
            return {
                qqq: 'Вы загрузили эту страницу: ' + new Date().toLocaleString()
            }
          }
    }
</script>

<style>

</style>