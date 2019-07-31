import {createStackNavigator, createAppContainer} from 'react-navigation';
import List from './components/List'
import EditDelete from './components/EditDelete'

const MainNavigator = createStackNavigator({
    List: {screen: List},
    EditDelete: {screen: EditDelete},
});

const App = createAppContainer(MainNavigator);

export default App;
