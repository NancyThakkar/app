//This is an example code for Navigator//
import React, { Component } from 'react';
//import react in our code.
//import all the components we are going to use.
import ToastExample from '../ToastExample';
import DateTimePicker from "react-native-modal-datetime-picker";
import WeekdayPicker from './WeekdayPicker';
import {StyleSheet, FlatList, Text, View, Alert, TouchableOpacity, TextInput, Image,ToolbarAndroid,Button,BackHandler} from 'react-native';
import { TimePicker, DatePicker } from 'react-native-wheel-picker-android';
export default class NewAlarm extends Component {

    static navigationOptions = {
        title: 'Priome',
        headerTintColor: "white",
        headerStyle: {
            backgroundColor: "#008577"
        },
        headerTitleStyle: {
            fontSize: 18
        }
    };

    constructor(props) {
        super(props);
        this.handleBackButtonClick = this.handleBackButtonClick.bind(this);
        this.state = {
            isDateTimePickerVisible: true,
            timePickerModeAndroid: 'spinner',
            hour:'',
            minute:'',
            id:'',
            days:{ 1:0, 2:0 , 3:0 , 4:0 , 5:0, 6:0, 0:0 },
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
    handleDatePicked = date => {
        console.log("A date has been picked: ", date);
       // ToastExample.show(date.getDay()+""+date.getMonth()+date.getHours()+""+date.getMinutes(),ToastExample.SHORT);
        var dat = new Date();
        var month = dat.getMonth() + 1;
        dat = date;
        let days = {1: 0, 2: 0, 3: 0, 4: 0, 5: 0, 6: 0, 0: 0};
        this.state.hour=dat.getHours();
        this.state.minute=dat.getMinutes();
    };
    handleChange = (days) => {
        this.state.days=days;
        this.setState(days);
    }
    SaveAlarm = () => {
        var dat = new Date();
        ToastExample.saveAlarm(dat.getDate(), dat.getMonth() + 1, this.state.hour, this.state.minute, 0, this.state.days);
        { this.props.navigation.replace('List')};
    }
    render() {
        return (
            <View style={styles.MainContainers}>
                <TimePicker
                    onTimeSelected={this.handleDatePicked}
                />
                <WeekdayPicker
                    days={this.state.days}
                    onChange={this.handleChange}
                />
                <TouchableOpacity activeOpacity={0.5} onPress={this.SaveAlarm} style={styles.button} >
                    <Text style = {styles.buttonText}>
                        Save
                    </Text>
                </TouchableOpacity>
            </View>
        );
    }

}


const styles = StyleSheet.create({
    toolbar: {
        backgroundColor: '#2196F3',
        height: 56,
        alignSelf: 'stretch',
        textAlign: 'center',
    },
    MainContainers: {
        flex: 1,
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'center',
        justifyContent: 'center',
    },

    textInputStyle: {
        textAlign: 'center',
        height: 40,
        width: '90%',
        borderWidth: 1,
        borderColor: '#4CAF50',
        borderRadius: 7,
        marginTop: 12
    },

    button: {
        width: '40%',
        height: 40,
        padding: 10,
        backgroundColor: '#90A4AE',
        borderRadius: 8,
        margin: 10,
        justifyContent: 'center',
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
    }

});

