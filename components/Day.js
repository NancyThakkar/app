import React from 'react';
import {
  Text,
  TouchableOpacity,
  StyleSheet
} from 'react-native';

export default function Day(props) {
  let daysMapping = {0: 'Su', 1:'M', 2: 'Tu', 3: 'W', 4:'Th', 5:'F', 6:'Sa' }
  return (
    <TouchableOpacity
      style={ [props.style, styles.default, props.isActive ? styles.active : styles.inactive]}
      onPress={() => props.toggleDay(props.day)}
    >
      <Text style={props.isActive ? styles.activeText : styles.inactiveText}>
        {daysMapping[props.day]}
      </Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  default:{
    height: 35,
    width: 35,
    borderRadius: 35,
    alignItems: 'center',
    justifyContent: 'center'
  },
  active: {
    backgroundColor: '#303F9F'
  },
  inactive: {
    backgroundColor: '#AAAAAA'
  },
  activeText: {
    color: '#ffffff'
  },
  inactiveText: {
    color: '#ffffff'
  }
});
