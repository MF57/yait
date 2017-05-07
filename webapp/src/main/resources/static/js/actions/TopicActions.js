import {UPVOTE_TOPIC} from "./TopicActionTypes";
export function upvoteTopic(id) {
    return {
        type: UPVOTE_TOPIC,
        id: id
    };
}