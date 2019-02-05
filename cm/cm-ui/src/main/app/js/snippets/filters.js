import Vue from 'vue'
import { DateTime } from 'luxon'



// Filters
Vue.filter('formatDateTime', function(value) {
  if (value) {
    return DateTime.fromISO(value).toFormat('yyyy-MM-dd HH:mm');
  }
});

// Return full file resource url by filename
Vue.filter('toFileUrl', function(filename) {
  if (filename) {
    return '/files/' + filename;
  }

  return ''
})

// Return full challenge picture file resource url by filename, or default challenge picture url if not found.
Vue.filter('toChallengePictureUrl', function(filename) {
  if (filename) {
    return '/files/' + filename;
  }

  return '/img/default-challenge-picture.jpg'
})