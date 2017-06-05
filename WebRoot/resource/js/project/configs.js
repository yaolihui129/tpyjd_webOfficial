requirejs.config({
    baseUrl: '/resource/js',
    shim: {
        jquery: {
            exports: "$"
        },
        common: {
            deps: ['jquery','bxslider']
        },
        yzm: {
            deps: ['jquery']
        },
        formValidate: {
            deps: ['jquery']
        },
        lay: {
            deps: ['jquery']
        },
        tab: {
            deps: ['jquery']
        },
        bxslider: {
            deps: ['jquery']
        },
         lyxAlert: {
            deps: ['jquery']
        }

    },
    paths: {
        'jquery': 'system/jquery-2.2.0.min',
        'common': 'modules/common',
        'yzm': 'modules/yzm',
        'formValidate': 'modules/formValidate',
        'lay': 'system/layer',
        'tab': 'modules/tab',
        'bxslider': 'modules/jquery.bxslider',
        'lyxAlert': 'modules/lyxAlert'
    }
});