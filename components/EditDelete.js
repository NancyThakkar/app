//This is an example code for Navigator//
import React, { Component } from 'react';
//import react in our code.
import { StyleSheet, View, Text,Button,TouchableOpacity,BackHandler} from 'react-native';
//import all the components we are going to use.
import ToastExample from '../ToastExample';
import DateTimePicker from "react-native-modal-datetime-picker";
import WeekdayPicker from './WeekdayPicker';

export default class EditDelete extends Component {
    static navigationOptions = {
        title: 'Priome',
        headerTintColor: "white",
        headerStyle: {
            backgroundColor:"#008577"
        },
        headerTitleStyle: {
            fontSize: 18
        }
    };
    constructor(props) {
        super(props)
        this.handleBackButtonClick = this.handleBackButtonClick.bind(this);
        this.state = {
            isDateTimePickerVisible: false,
            timePickerModeAndroid:'spinner',
            item: this.props.navigation.state.params.item,
            hour:'',
            minute:'',
            id:'',
            days:this.props.navigation.state.params.dayss,

        };
    }

    componentWillMount() {
        BackHandler.addEventListener('hardwareBackPress', this.handleBackButtonClick);
    }

    componentWillUnmount() {
        BackHandler.removeEventListener('hardwareBackPress', this.handleBackButtonClick);
    }

    handleBackButtonClick() {
       // this.props.navigation.goBack(null);
        { this.props.navigation.replace('List')};
        return true;
    }

    displayMessage(message) {
          ToastExample.delete(message);
        { this.props.navigation.replace('List')}
    }

    showDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: true });
    };

    hideDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: false });
    }
    handleChange = (days) => {
        //ToastExample.show("sunil"+days("0"),ToastExample.SHORT);
        this.state.days=days;
        this.setState(days)
    }
    handleDatePicked = date => {
        console.log("A date has been picked: ", date);
        this.hideDateTimePicker();
        var dat = new Date();
        dat=date;
        this.state.hour=dat.getHours();
        this.state.minute=dat.getMinutes();
       // ToastExample.show(""+dat.getHours()+""+dat.getMinutes(),ToastExample.SHORT);
        ToastExample.updateAlarm(this.state.id,dat.getDate(),dat.getMonth()+1,dat.getHours(),dat.getMinutes(),0,this.state.days);
        {this.props.navigation.replace('List')}
    };
    render() {
/*        const navigate  = this.props.navigation;
        const item = navigate.getParam('item', 'item');*/
        var str=this.state.item.title;
        var id=str.split("          ")[1];
        this.state.id=id;
        var time=str.split(" ")[2];
        let days = { 1:0, 2:0 , 3:0 , 4:0 , 5:0, 6:0, 0:0 };
        return (
            <View style={styles.container}>
            <Text style={styles.item}>{time}</Text>
                <DateTimePicker
                    timePickerModeAndroid={this.state.timePickerModeAndroid}
                    mode='time'
                    isVisible={this.state.isDateTimePickerVisible}
                    onConfirm= {this.handleDatePicked}
                    onCancel={this.hideDateTimePicker}
                />
                <WeekdayPicker
                    days={this.state.days}
                    onChange={this.handleChange}
                />
                <TouchableOpacity activeOpacity={0.5} onPress={this.showDateTimePicker} style={styles.button} >
                    <Text style = {styles.buttonText}>
                        Edit
                    </Text>
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.5} onPress={this.displayMessage.bind(this, ""+id)} style={styles.button} >
                    <Text style = {styles.buttonText}>
                        Delete
                    </Text>
                </TouchableOpacity>
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
    }, item: {
        padding: 10,
        fontSize: 18,
        height: 44,
    },
    button: {
        width: '40%',
        height: 40,
        padding: 10,
        backgroundColor: '#90A4AE',
        borderRadius: 8,
        margin: 10
    },
        TouchableOpacityStyle:{

        position: 'absolute',
        width: 50,
        height: 50,
        alignItems: 'center',
        justifyContent: 'center',
        right: 30,
        bottom: 30,
    },
    buttonText: {
        color: '#fff',
        textAlign: 'center',
    },
});
