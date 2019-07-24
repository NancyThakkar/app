import React, { Component } from 'react';

import { StyleSheet, View, Image, TouchableOpacity, Alert } from 'react-native';
import DateTimePicker from "react-native-modal-datetime-picker";
export default class App extends Component<{}> {

    constructor(props) {
        super(props);
        this.state = {
            isDateTimePickerVisible: false
        };
    }

    SampleFunction=()=>{

        Alert.alert("Floating Button Clicked");

    }


    showDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: true });
    };

    hideDateTimePicker = () => {
        this.setState({ isDateTimePickerVisible: false });
    };

    handleDatePicked = date => {
        console.log("A date has been picked: ", date);
        this.hideDateTimePicker();
    };


    render() {

        return (

            <View style={styles.MainContainer}>
                <DateTimePicker
                    mode='time'
                    isVisible={this.state.isDateTimePickerVisible}
                    onConfirm={this.handleDatePicked}
                    onCancel={this.hideDateTimePicker}
                />
                <TouchableOpacity activeOpacity={0.5} onPress={this.showDateTimePicker} style={styles.TouchableOpacityStyle} >

                    <Image source={{uri : 'https://reactnativecode.com/wp-content/uploads/2017/11/Floating_Button.png'}}

                           style={styles.FloatingButtonStyle} />

                </TouchableOpacity>

            </View>
        );
    }
}

const styles = StyleSheet.create({

    MainContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor : '#F5F5F5'
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

    FloatingButtonStyle: {

        resizeMode: 'contain',
        width: 50,
        height: 50,
    }
});
