import {UPVOTE_TOPIC} from "../actions/TopicActionTypes";
export function topicReducer(state, action) {
    switch (action.type){
        case UPVOTE_TOPIC: {
            let newTopics = upvoteTopic(action.id, state.topics);
            let st = Object.assign({}, state, {topics: newTopics});
            return st;
        }
    }
    return state;
};

let upvoteTopic = function(id, topics) {
    let newTopics = [];
    for(let i = 0; i < topics.length; i++) {
        if(topics[i].id == id){
            newTopics.push(Object.assign({}, topics[i], {score: topics[i].score+1}));
        } else {
            newTopics.push(topics[i]);
        }
    }
    return newTopics;
};