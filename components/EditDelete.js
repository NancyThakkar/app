//This is an example code for Navigator//
import React, { Component } from 'react';
//import react in our code.
import { StyleSheet, View, Text,Button,TouchableOpacity,BackHandler} from 'react-native';
//import all the components we are going to use.
import ToastExample from '../ToastExample';
import DateTimePicker from "react-native-modal-datetime-picker";
import WeekdayPicker from './WeekdayPicker';
import { TimePicker } from 'react-native-wheel-picker-android';

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
            isDateTimePickerVisible: true,
            timePickerModeAndroid: 'spinner',
            hour:'',
            minute:'',
            id:'',
            item: this.props.navigation.state.params.item,
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

    deletealarm(id) {
          ToastExample.delete(id);
        { this.props.navigation.replace('List')}
    }
    handleChange = (days) => {
        //ToastExample.show("days"+days("0"),ToastExample.SHORT);
        this.state.days=days;
        this.setState(days)
    }
    handleDatePicked = date => {
        var dat = new Date();
        var month = dat.getMonth() + 1;
        dat = date;
        this.state.hour=dat.getHours();
        this.state.minute=dat.getMinutes();
    };
    EditAlarm = () => {
        var dat = new Date();
        ToastExample.updateAlarm(this.state.id,dat.getDate(),dat.getMonth()+1,this.state.hour,this.state.minute,0,this.state.days);
        {this.props.navigation.replace('List')}
    }
    render() {
        var str=this.state.item.title;
        var id=str.split("          ")[1];
        this.state.id=id;
        var time=str.split(" ")[2];
        let days = { 1:0, 2:0 , 3:0 , 4:0 , 5:0, 6:0, 0:0 };
        const GLOBAL = require('../Globals');
        return (
        <View style={styles.container}>
                <TimePicker
                    minutes={GLOBAL.MinArray}
                    onTimeSelected={this.handleDatePicked}
                />
                <WeekdayPicker
                    days={this.state.days}
                    onChange={this.handleChange}
                />
                <TouchableOpacity activeOpacity={0.5} onPress={this.EditAlarm} style={styles.button} >
                    <Text style = {styles.buttonText}>
                        Save
                    </Text>
                </TouchableOpacity>
                <TouchableOpacity activeOpacity={0.5} onPress={this.deletealarm.bind(this, ""+id)} style={styles.button} >
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
        backgroundColor: '#AAAAAA',
        borderRadius: 8,
        margin: 10
    },
    buttonText: {
        color: '#fff',
        textAlign: 'center',
    },
});
