module.exports = function(api) {
  api.cache(true);
  return {
    presets: ['babel-preset-expo'],
    plugins: [
      ['module-resolver', {
        root: ['./src'],
        alias: {
          'components': './src/components',
          'navigations': './src/navigations',
          'actions': './src/actions',
          'reducers': './src/reducers',
        }
      }]
    ]
  };
};
