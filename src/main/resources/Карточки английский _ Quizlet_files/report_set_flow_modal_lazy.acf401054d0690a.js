(window.QJP=window.QJP||[]).push([["report_set_flow_modal_lazy"],{"0qdk":function(e,t,n){"use strict";n.d(t,"b",(function(){return r})),n.d(t,"a",(function(){return a}));var r,s=n("mEm4");!function(e){e[e.NONE=0]="NONE",e[e.SHOULD_REQUEST=1]="SHOULD_REQUEST",e[e.AWAITING_RESPONSE=2]="AWAITING_RESPONSE",e[e.SUCCESS=3]="SUCCESS",e[e.TIMEOUT=4]="TIMEOUT",e[e.SERVER_ERROR=5]="SERVER_ERROR"}(r||(r={}));var a=Object(s.a)("REPORT_MODAL",{UPDATE_ANSWER:null,UPDATE_ASYNC_REQUEST_STATUS:null,UPDATE_REASON:null,UPDATE_SHOW_ASYNC_STATUS:null,POP_PANEL:null,PUSH_PANEL:null,SUBMIT_PANEL:null})},"36r5":function(e,t,n){"use strict";n.d(t,"a",(function(){return l}));var r=n("dxfC"),s=n("9FQb"),a=n("q1tI"),o=n.n(a),i=n("Vm2m");class l extends a.PureComponent{UNSAFE_componentWillMount(){Object(i.a)(this.props.initialPanelKey)}render(){return o.a.createElement(r.a,{headerContent:this.props.headerContent,isOpen:this.props.isOpen,onClose:this.props.onClose},o.a.createElement(s.a,{getNextStep:this.props.getNextStep,getPanelFromKey:this.props.getPanelFromKey,imageIds:this.props.imageIds,isReportingFlowBlocked:this.props.isReportingFlowBlocked,modelId:this.props.modelId,modelType:this.props.modelType}))}}},"8wS+":function(e,t,n){"use strict";n.r(t),n.d(t,"default",(function(){return p}));var r=n("36r5"),s=n("t1Ez"),a=n("GCXI"),o=n("q1tI"),i=n.n(o),l=n("IAI4");class p extends o.PureComponent{render(){return i.a.createElement(r.a,{getNextStep:l.a.getNextStep,getPanelFromKey:l.a.getPanelFromKey,headerContent:i.a.createElement(s.a,{id:"reporting_flow.set.title"}),initialPanelKey:l.a.initialPanelKey,isOpen:this.props.isOpen,isReportingFlowBlocked:this.props.isReportingFlowBlocked,modelId:this.props.modelId,modelType:a.L.SET,onClose:this.props.onClose})}}},"9FQb":function(e,t,n){"use strict";n("4mDm"),n("3bBZ"),n("zKZe"),n("SYor");var r=n("nrsj"),s=n("baRz"),a=n("t1Ez"),o=n("JxFz"),i=n("Pxp3"),l=n("vlgG"),p=n("47yS"),u=n("mM5Q"),c=n("zt8I"),b=n("wcmh"),_=n("OpqZ"),d=n("GCXI"),h=n("JPcv"),g=n("q1tI"),E=n.n(g),A=n("0qdk"),m=Object(s.styled)("div")({name:"ImageCarouselContainer",class:"ivav9py"}),y=Object(s.styled)("img")({name:"ImageCarousel",class:"ilv83ip"}),S=Object(s.styled)("div")({name:"ImageSection",class:"ilqdo50"}),I=Object(s.styled)("div")({name:"AnswerSection",class:"aeyg9jv"}),O=Object(s.styled)("p")({name:"QuestionTitle",class:"q1n5whmq"}),T=Object(s.styled)("div")({name:"TextQuestion",class:"t1oy0yfs"}),w=Object(s.styled)("div")({name:"ButtonSection",class:"b8nqvvw"}),R=Object(s.styled)("section")({name:"QuestionPanel",class:"q1agn7ib"});class P extends E.a.PureComponent{constructor(){super(...arguments),this.state={optionSelected:this.props.imageIds?this.props.imageIds[0].id:0},this.handleUpdateAnswer=e=>{var t=e.target.value,n=e.target.name;this.props.actions.onUpdateAnswer({question:n,answer:t})},this.handleSubmitPanel=()=>{var e={panelKey:this.getCurrentPanelKey(),answers:this.props.answers,getPanelFromKey:this.props.getPanelFromKey,getNextStep:this.props.getNextStep,history:this.props.history,prevReason:this.props.reason,modelType:this.props.modelType,modelId:this.props.modelId};if(this.isImageReport()){var t=this.getImageSelected();this.props.actions.onSubmitPanel(Object.assign({},e,{modelId:null==t?void 0:t.id,sideText:null==t?void 0:t.sideText,imageUrl:null==t?void 0:t.url,page:null==t?void 0:t.page}))}else this.props.actions.onSubmitPanel(e)},this.handleImageSelection=e=>{var t=Number(e.target.value);this.setState({optionSelected:t})}}getCurrentPanel(){return this.props.getPanelFromKey(this.getCurrentPanelKey())}getCurrentPanelKey(){return this.props.history.last()}getImageId(){return this.state.optionSelected}getImageSelected(){var e;return null==(e=this.props.imageIds)?void 0:e.find((e=>e.id===this.getImageId()))}getTextAnswerFor(e){var t=this.props.answers.get(e);return void 0===t?"":t}renderBackButton(){return E.a.createElement(o.a,{"aria-label":Object(r.a)("reporting_flow.navigation.back_button"),onClick:this.props.actions.onGoBack},E.a.createElement(a.a,{id:"reporting_flow.navigation.back_button"}))}renderContinueButton(){return E.a.createElement(o.a,{"aria-label":Object(r.a)("reporting_flow.navigation.continue_button"),disabled:this.shouldDisableContinueButton(),onClick:this.handleSubmitPanel},E.a.createElement(a.a,{id:"reporting_flow.navigation.continue_button"}))}renderImageSelection(){if(this.isImageReport()){var e=this.props.imageIds||[],t=this.state.optionSelected;return E.a.createElement(m,null,e.map((e=>E.a.createElement(b.a,{checked:e.id===t,key:e.id,label:E.a.createElement(y,{alt:e.sideText,src:e.url}),onChange:this.handleImageSelection,value:e.id}))))}return null}renderQuestionPanel(e){var t=e.questions;return E.a.createElement(R,null,this.renderImageSection(),this.renderQuestions(t),E.a.createElement(w,null,this.shouldShowBackButton()?this.renderBackButton():null,this.renderContinueButton()))}renderImageSection(){return this.isImageReport()?E.a.createElement(S,null,this.renderImageSelectionTitle(),this.renderImageSelection()):null}renderQuestions(e){return E.a.createElement("div",null,e.map(((t,n)=>E.a.createElement(_.a,{key:t.key},n>0&&e[n-1].type===d.tb.RADIO&&t.type===d.tb.RADIO?E.a.createElement(u.a,null):null,this.renderQuestion(t)))))}renderQuestion(e){return e.type===d.tb.RADIO?this.renderRadioQuestion(e):this.renderTextQuestion(e)}renderRadioQuestion(e){return E.a.createElement(i.a,null,this.renderQuestionTitle(e),this.renderQuestionOptions(e))}renderQuestionOptions(e){return E.a.createElement(I,null,e.options.map((t=>E.a.createElement(_.a,{key:t.key},E.a.createElement(b.a,{checked:t.key===this.props.answers.get(e.key),label:t.text,name:""+e.key,onChange:this.handleUpdateAnswer,value:t.key})))))}renderTextQuestion(e){return E.a.createElement(T,null,E.a.createElement(l.a,{label:e.question,name:""+e.key,onChange:this.handleUpdateAnswer,placeholder:e.placeholder,type:"autoExpand",value:this.getTextAnswerFor(e.key)}))}renderImageSelectionTitle(){return E.a.createElement(O,null,Object(r.a)("reporting_flow.image.questions.which_image.question"))}renderQuestionTitle(e){return E.a.createElement(O,null,e.question)}renderPanel(e){switch(e.type){case d.sb.QUESTION:return this.renderQuestionPanel(e);default:return this.renderMessagePanel(e)}}renderMessagePanel(e){return E.a.createElement(_.a,null,this.renderResult(e),e.buttons?this.renderMessagePanelButtons(e):null)}renderResult(e){if(!this.props.showAsyncStatus)return this.renderMessage(e);switch(this.props.requestStatus){case A.b.AWAITING_RESPONSE:return E.a.createElement(p.a,null);case A.b.TIMEOUT:return E.a.createElement(c.a,null,E.a.createElement(a.a,{id:"reporting_flow.general.errors.timeout"}));case A.b.SERVER_ERROR:return E.a.createElement(c.a,null,E.a.createElement(a.a,{id:"reporting_flow.general.errors.server"}));case A.b.SUCCESS:return this.renderMessage(e);default:return null}}renderMessage(e){return E.a.createElement(c.a,null,e.message)}renderMessagePanelButtons(e){return E.a.createElement(c.a,null,e.buttons.map((e=>{if(this.props.isMobileWebView&&void 0!==e.mobileHandler){if(!1===e.mobileHandler)return null;var t=!0===e.mobileHandler?void 0:e.mobileHandler;return E.a.createElement(o.a,{"aria-label":e.title,key:e.url,onClick:t},e.title)}return E.a.createElement(o.a,{"aria-label":e.title,key:e.url,linkTo:e.url,linkToNewTab:!this.props.isMobileWebView&&!!e.newTab},e.title)})))}render(){if(this.props.isReportingFlowBlocked)return E.a.createElement(c.a,null,E.a.createElement(a.a,{id:"reporting_flow.general.errors.blocked"}));var e=this.getCurrentPanel();return E.a.createElement("div",{className:"ReportModal-main"},this.renderPanel(e))}isImageReport(){return this.props.modelType===d.L.IMAGE}shouldDisableContinueButton(){for(var e=this.getCurrentPanel(),t=this.props.answers,n=!!this.state.optionSelected,r=!0,s=!1,a=0;a<e.questions.length;a++){var o=e.questions[a].key;switch(e.questions[a].type){case d.tb.RADIO:if(void 0===t.get(o)||null===t.get(o))return!0;r=!1;break;case d.tb.TEXT:""===this.getTextAnswerFor(o).trim()&&(s=!0)}}return this.isImageReport()?s||!n:r&&s}shouldShowBackButton(){return this.props.history.size>1}}P.defaultProps={answers:Object(h.Map)(),isMobileWebView:!1,isReportingFlowBlocked:!1,reason:null,requestStatus:A.b.NONE,showAsyncStatus:!0};var f=P;n("iD+L");var N=n("4gbl"),k=n("Vg22"),v=n("pn57"),C=n("yXPU"),U=n.n(C),q=n("SQJ4");class j extends q.a{static report(e){return U()((function*(){var t=yield j.post(e);return t&&t.length&&t[0]&&t[0].data&&t[0].data.userReport?t[0].data.userReport:null}))()}}j.URL_BASE="report",j.API_VERSION="3.2",j.CACHE=!1,j.ALLOW_MULTIPLE_REQUESTS=!1,j.REQUEST_TIMEOUT_LIMIT_MS=25e3;var H=n("YDJX"),L=n("T3U7"),x=Object(H.createAction)(A.a.UPDATE_ANSWER),M=Object(H.createAction)(A.a.UPDATE_ASYNC_REQUEST_STATUS),D=Object(H.createAction)(A.a.UPDATE_REASON),G=Object(H.createAction)(A.a.UPDATE_SHOW_ASYNC_STATUS),W=Object(H.createAction)(A.a.POP_PANEL),Q=Object(H.createAction)(A.a.PUSH_PANEL),Y=e=>t=>{t(M(A.b.AWAITING_RESPONSE)),j.report(e).then((e=>t(M(e?A.b.SUCCESS:A.b.SERVER_ERROR))),(e=>t(M(null!=e&&e.timeout?A.b.TIMEOUT:A.b.SERVER_ERROR))))},F=e=>t=>{var n=e.getNextStep(e.panelKey,e.answers);if(n){t(G(n.showAsyncStatus||!1)),t(D(n.reason)),t(Q(n.panel));var r=!1;if(n.reason&&null===e.prevReason){r=!0;var s=((e,t,n)=>{for(var r={},s=0;s<n.size;s++){var a=e(n.get(s));if(a.type===d.sb.QUESTION)for(var o=0;o<a.questions.length;o++){var i=a.questions[o].key;r[i]=t.get(i)}}return r})(e.getPanelFromKey,e.answers,e.history);e.modelType===d.L.IMAGE&&null!==e.sideText?t(Y({answers:s,modelType:e.modelType,modelId:e.modelId,reason:n.reason,sideText:e.sideText,imageUrl:e.imageUrl,page:e.page})):t(Y({answers:s,modelType:e.modelType,modelId:e.modelId,reason:n.reason}))}var a={modelType:e.modelType,modelId:e.modelId,panel:n.panel,reason:void 0};r?(a.reason=n.reason,Object(L.a)("finish_reporting_flow",a)):Object(L.a)("continue_reporting_flow",a)}},B=n("peh1"),V=e=>e.get("history"),K=(Object(B.createSelector)(V,(e=>e.last())),n("FdmV")),X=Object(K.a)("reportingFlow",{answers:e=>e.get("answers"),history:V,reason:e=>e.get("reason"),requestStatus:e=>e.get("requestStatus"),showAsyncStatus:e=>e.get("showAsyncStatus")});t.a=Object(k.connect)(X,(e=>({actions:Object(v.bindActionCreators)({onGoBack:W,onUpdateAnswer:x,onSubmitPanel:F},e)})),null,{context:N.a})(f)},FdmV:function(e,t,n){"use strict";n.d(t,"a",(function(){return s}));var r=n("peh1");function s(e,t){return Object(r.createSelector)((t=>t.get(e)),((e,t)=>t),(e=>e),Object(r.createStructuredSelector)(t))}},IAI4:function(e,t,n){"use strict";var r=n("44Ds"),s=n.n(r),a=n("GCXI"),o=n("nrsj"),i=s()((()=>({[a.nb.WHAT_IS_WRONG]:{type:a.tb.RADIO,key:a.nb.WHAT_IS_WRONG,question:Object(o.a)("reporting_flow.set.questions.what_is_wrong.question"),options:[{key:a.mb.INACCURATE,text:Object(o.a)("reporting_flow.set.questions.what_is_wrong.options.inaccurate")},{key:a.mb.INAPPROPRIATE,text:Object(o.a)("reporting_flow.set.questions.what_is_wrong.options.inappropriate")},{key:a.mb.CHEATING,text:Object(o.a)("reporting_flow.set.questions.what_is_wrong.options.cheating")},{key:a.mb.INTELLECTUAL_PROPERTY,text:Object(o.a)("reporting_flow.set.questions.what_is_wrong.options.intellectual_property")}]},[a.nb.WHY_INAPPROPRIATE]:{type:a.tb.RADIO,key:a.nb.WHY_INAPPROPRIATE,question:Object(o.a)("reporting_flow.set.questions.why_inappropriate.question"),options:[{key:a.hb.BAD_LANGUAGE,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.bad_language")},{key:a.hb.MATURE,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.mature")},{key:a.hb.SEXUALLY_EXPLICIT,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.sexually_explicit")},{key:a.hb.HARASS_BULLY_OR_HATE,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.harass_bully_or_hate")},{key:a.hb.SUICIDAL,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.suicidal")},{key:a.hb.CHAT,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.chat")},{key:a.hb.SPAM,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.spam")},{key:a.hb.OTHER,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate.options.other")}]},[a.nb.WHY_INAPPROPRIATE_OTHER]:{type:a.tb.RADIO,key:a.nb.WHY_INAPPROPRIATE_OTHER,question:Object(o.a)("reporting_flow.set.questions.why_inappropriate.question"),options:[{key:a.ib.VIOLENT_THREATENING_OR_SELF_HARM,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate_other.options.violent_threatening_or_self_harm")},{key:a.ib.PHISHING,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate_other.options.phishing")},{key:a.ib.PRIVACY_VIOLATION,text:Object(o.a)("reporting_flow.general.questions.why_inappropriate_other.options.privacy_violation")}]},[a.nb.WHOSE_PRIVACY]:{type:a.tb.RADIO,key:a.nb.WHOSE_PRIVACY,question:Object(o.a)("reporting_flow.general.questions.whose_privacy.question"),options:[{key:a.gb.MINE,text:Object(o.a)("reporting_flow.general.questions.whose_privacy.options.mine")},{key:a.gb.SOMEONE_ELSE,text:Object(o.a)("reporting_flow.general.questions.whose_privacy.options.someone_else")}]},[a.nb.WHAT_PRIVATE_INFO]:{type:a.tb.RADIO,key:a.nb.WHAT_PRIVATE_INFO,question:Object(o.a)("reporting_flow.set.questions.what_private_info.question"),options:[{key:a.fb.PII,text:Object(o.a)("reporting_flow.general.questions.what_private_info.options.pii")},{key:a.fb.IMAGES,text:Object(o.a)("reporting_flow.general.questions.what_private_info.options.images")},{key:a.fb.OTHER,text:Object(o.a)("reporting_flow.general.questions.what_private_info.options.other")}]},[a.nb.PRIVACY_DETAILS]:{type:a.tb.TEXT,key:a.nb.PRIVACY_DETAILS,question:Object(o.a)("reporting_flow.general.questions.privacy_details.question"),placeholder:Object(o.a)("reporting_flow.general.questions.privacy_details.placeholder")}}))),l="what_is_wrong",p="why_inappropriate",u="why_inappropriate_other",c="privacy",b="thanks",_="ugc",d="test_bank",h="dmca",g="thanks_suicidal",E=e=>e.map((e=>i()[e])),A=s()((()=>({[l]:{key:l,type:a.sb.QUESTION,questions:E([a.nb.WHAT_IS_WRONG])},[p]:{key:p,type:a.sb.QUESTION,questions:E([a.nb.WHY_INAPPROPRIATE])},[u]:{key:u,type:a.sb.QUESTION,questions:E([a.nb.WHY_INAPPROPRIATE_OTHER])},[c]:{key:c,type:a.sb.QUESTION,questions:E([a.nb.WHOSE_PRIVACY,a.nb.WHAT_PRIVATE_INFO,a.nb.PRIVACY_DETAILS])},[b]:{key:b,type:a.sb.MESSAGE,message:Object(o.a)("reporting_flow.general.messages.thanks.body"),buttons:[{title:Object(o.a)("reporting_flow.general.messages.thanks.buttons.home"),url:"/latest",mobileHandler:!1}]},[_]:{key:_,type:a.sb.MESSAGE,message:Object(o.a)("reporting_flow.general.messages.ugc.body"),buttons:[{title:Object(o.a)("reporting_flow.general.messages.ugc.buttons.typos_faq"),url:"/help/2444157/how-do-i-report-a-typo-in-a-set",newTab:!0}]},[d]:{key:d,type:a.sb.MESSAGE,message:Object(o.a)("reporting_flow.set.messages.test_bank.body"),buttons:[{title:Object(o.a)("reporting_flow.set.messages.test_bank.buttons.report_test_bank"),url:"/testbank/request",newTab:!0}]},[h]:{key:h,type:a.sb.MESSAGE,message:Object(o.a)("reporting_flow.general.messages.dmca.body"),buttons:[{title:Object(o.a)("reporting_flow.general.messages.dmca.buttons.dmca_policy"),url:"/dmca",newTab:!0}]},[g]:{key:g,type:a.sb.MESSAGE,message:Object(o.a)("reporting_flow.general.messages.thanks_suicidal.body"),buttons:[{title:Object(o.a)("reporting_flow.general.messages.thanks_suicidal.buttons.self_harm_help_center"),url:"/help/2444162/self-harm-prevention-and-user-safety",newTab:!0},{key:"home",title:Object(o.a)("reporting_flow.general.messages.thanks_suicidal.buttons.home"),url:"/latest",mobileHandler:!1}]}}))),m={initialPanelKey:l,getPanelFromKey:e=>A()[e],getNextStep(e,t){switch(e){case l:switch(t.get(a.nb.WHAT_IS_WRONG)){case a.mb.INACCURATE:return{panel:_,reason:a.lb.INACCURATE,showAsyncStatus:!1};case a.mb.INAPPROPRIATE:return{panel:p,reason:null};case a.mb.CHEATING:return{panel:d,reason:a.lb.CHEATING,showAsyncStatus:!1};case a.mb.INTELLECTUAL_PROPERTY:return{panel:h,reason:a.lb.INTELLECTUAL_PROPERTY,showAsyncStatus:!1}}break;case p:switch(t.get(a.nb.WHY_INAPPROPRIATE)){case a.hb.OTHER:return{panel:u,reason:null};case a.hb.BAD_LANGUAGE:return{panel:b,reason:a.lb.BAD_LANGUAGE,showAsyncStatus:!0};case a.hb.MATURE:return{panel:b,reason:a.lb.MATURE,showAsyncStatus:!0};case a.hb.SEXUALLY_EXPLICIT:return{panel:b,reason:a.lb.SEXUALLY_EXPLICIT,showAsyncStatus:!0};case a.hb.HARASS_BULLY_OR_HATE:return{panel:b,reason:a.lb.HARASS_BULLY_OR_HATE,showAsyncStatus:!0};case a.hb.SUICIDAL:return{panel:g,reason:a.lb.SUICIDAL,showAsyncStatus:!0};case a.hb.CHAT:return{panel:b,reason:a.lb.CHAT,showAsyncStatus:!0};case a.hb.SPAM:return{panel:b,reason:a.lb.SPAM,showAsyncStatus:!0}}break;case u:switch(t.get(a.nb.WHY_INAPPROPRIATE_OTHER)){case a.ib.PRIVACY_VIOLATION:return{panel:c,reason:null};case a.ib.VIOLENT_THREATENING_OR_SELF_HARM:return{panel:b,reason:a.lb.VIOLENT_THREATENING_OR_SELF_HARM,showAsyncStatus:!0};case a.ib.PHISHING:return{panel:b,reason:a.lb.PHISHING,showAsyncStatus:!0}}break;case c:return{panel:b,reason:a.lb.PRIVACY_VIOLATION,showAsyncStatus:!0}}return null}};t.a=m},Vm2m:function(e,t,n){"use strict";n.d(t,"a",(function(){return l}));var r=n("F4TK"),s=n("JPcv"),a=n("YDJX"),o=n("0qdk"),i={[o.a.UPDATE_ANSWER]:(e,t)=>{var{payload:n}=t,r=n.question,s=n.answer;return e.setIn(["answers",r],s)},[o.a.UPDATE_ASYNC_REQUEST_STATUS]:(e,t)=>e.set("requestStatus",t.payload),[o.a.UPDATE_REASON]:(e,t)=>e.set("reason",t.payload),[o.a.UPDATE_SHOW_ASYNC_STATUS]:(e,t)=>e.set("showAsyncStatus",t.payload),[o.a.POP_PANEL]:e=>{var t=e.get("history");return t.size>1?e.merge(Object(s.fromJS)({history:t.pop()})):e},[o.a.PUSH_PANEL]:(e,t)=>e.set("history",e.get("history").push(t.payload))};function l(e){var t=Object(a.handleActions)(i,(e=>Object(s.fromJS)({answers:{},history:[e],reason:null,requestStatus:null,showAsyncStatus:!0}))(e));r.a.registerReducer("reportingFlow",t)}},"iD+L":function(e,t,n){"use strict";n.r(t)}}]);