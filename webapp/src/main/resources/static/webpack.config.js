let webpack = require('webpack');
let path = require('path');

let BUILD_DIR = path.resolve(__dirname, 'build');
let APP_DIR = path.resolve(__dirname, 'js');

let config = {
    entry: APP_DIR + '/index.js',
    output: {
        path: BUILD_DIR,
        filename: 'bundle.js'
    },
    module: {
        loaders: [
            {
                test: /\.js?/,
                include: APP_DIR,
                loaders: ["react-hot-loader", "babel-loader"]
            }
        ],
    },
    devServer: {
        publicPath: "/",
        contentBase: ".",
        hot: true,
        historyApiFallback: true
    },
};
module.exports = config;