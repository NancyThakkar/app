//This is an example code for Navigator//
import React, { Component } from 'react';
//import react in our code.
import { StyleSheet, View, Text,Button} from 'react-native';
//import all the components we are going to use.
import ToastExample from '../ToastExample';

export default class EditDelete extends Component {
    static navigationOptions = {
        title: 'Priome',
        headerTintColor: "white",
        headerStyle: {
            backgroundColor:"blue"
        },
        headerTitleStyle: {
            fontSize: 18
        }
    };
    constructor(props) {
        super(props)

        this.state = {
            item: this.props.navigation.state.params.item

        };
    }
    displayMessage(message) {
          ToastExample.delete(message);
        { this.props.navigation.replace('List')}
    }
    render() {
/*        const navigate  = this.props.navigation;
        const item = navigate.getParam('item', 'item');*/
        var str=this.state.item.title;
        var id=str.split(" ")[1];
        return (
            <View style={styles.container}>
            <Text>{str}</Text>
                <Button
                    value={id}
                    onPress={this.displayMessage.bind(this, ""+id)}
                    title="Delete"
                    color="#841584"
                />
            </View>
    );
    }
}
const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});
