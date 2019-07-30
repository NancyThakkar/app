import {createStackNavigator, createAppContainer} from 'react-navigation';
import List from './components/List'
import SecondPage from './components/SecondPage'

const MainNavigator = createStackNavigator({
    List: {screen: List},
    SecondPage: {screen: SecondPage},
});

const App = createAppContainer(MainNavigator);

export default App;
