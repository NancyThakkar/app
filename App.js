import {createStackNavigator, createAppContainer} from 'react-navigation';
import List from './components/List'
import EditDelete from './components/EditDelete'
import NewAlarm from './components/NewAlarm'

const MainNavigator = createStackNavigator({
    List: {screen: List},
    EditDelete: {screen: EditDelete},
    NewAlarm: {screen: NewAlarm},
});

const App = createAppContainer(MainNavigator);

export default App;
