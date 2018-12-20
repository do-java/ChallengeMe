const path = require('path');
const CleanWebpackPlugin = require('clean-webpack-plugin');
const VueLoaderPlugin = require('vue-loader/lib/plugin');

module.exports = {
  entry: path.join(__dirname, 'src', 'main', 'resources', 'static', 'js', 'main.js'),
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: ['@babel/preset-env']
          }
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader'
      },
	{
	   test: /\.css$/,
	   use: [
		   'vue-style-loader',
		   'css-loader'
	   ]
	},
	{
		test: /\.scss$/,
		use: [
		  'vue-style-loader',
		  'css-loader',
		  'sass-loader'
		]
	  }
    ]
  },
  plugins: [
  	new CleanWebpackPlugin(),
    new VueLoaderPlugin()
  ],
  resolve: {
      modules: [
          path.join(__dirname, 'src', 'main', 'resources', 'static', 'js'),
          path.join(__dirname, 'src', 'main', 'resources', 'static', 'scss'),
          path.join(__dirname, 'node_modules'),
      ],
  }
}